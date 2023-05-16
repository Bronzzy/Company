package com.example.demo.entity;

import com.example.demo.serviceimp.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
@SqlResultSetMapping(
        name = "EmployeeStatistics",
        classes = {
                @ConstructorResult(
                        targetClass = com.example.demo.serviceimp.dto.CustomEmployeeDTO.class,
                        columns = {
                                @ColumnResult(name = "employeeId", type = String.class),
                                @ColumnResult(name = "dateOfBirth", type = LocalDate.class),
                                @ColumnResult(name = "firstName",type = String.class),
                                @ColumnResult(name = "middleName",type = String.class),
                                @ColumnResult(name = "lastName",type = String.class),
                                @ColumnResult(name = "gender", type = String.class),
                                @ColumnResult(name = "salary", type = Double.class)
//                                @ColumnResult(name = "deptID",type = Long.class)
                        }
                )
        }
)
@NamedNativeQuery(
        name = Employee.FIND_EMPLOYEE_WITH_MAX_SALARY,
        query = "SELECT e.employee_id as employeeId, e.date_of_birth as dateOfBirth, e.first_name as firstName, " +
                "e.middle_name as middleName, e.last_name as lastName, e.gender as gender, e.salary as salary, e.dept_id as deptID FROM employee e where e.salary in (select max(e2.salary) from employee e2)",
        resultSetMapping = "EmployeeStatistics"
)
public class Employee {

    public static final String FIND_EMPLOYEE_WITH_MAX_SALARY = "Employee.findEmployeeWithMaxSalary";
    @Id
    private String employeeId;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "salary")
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;
}
