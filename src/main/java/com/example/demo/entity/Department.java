package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Department")
@SqlResultSetMapping(
        name = "DepartmentStatistics",
        classes = {
                @ConstructorResult(
                        targetClass = com.example.demo.serviceimp.dto.DepartmentDTO.class,
                        columns = {
                                @ColumnResult(name = "id",type = Long.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "startDate", type = LocalDate.class)
                        }
                )
        }
)
@NamedNativeQuery(
        name = Department.FIND_DEPARTMENT_START_DATE_LESS_THAN,
        query = "SELECT d.id as id, d.department_name as name, d.start_date as startDate FROM Department d where d.start_date < :startDate",
        resultSetMapping = "DepartmentStatistics"
)
public class Department {

    public static final String FIND_DEPARTMENT_START_DATE_LESS_THAN = "findDepartmentStartDateLessThan";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @OneToMany(mappedBy = "department")
    private List<DepartmentLocation> departmentLocations;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(mappedBy = "department")
    private List<Project> projects;
}
