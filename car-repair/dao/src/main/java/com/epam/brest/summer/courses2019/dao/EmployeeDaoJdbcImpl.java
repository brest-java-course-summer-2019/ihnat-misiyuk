package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Employee;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Employee DAO Interface implementation.
 */
@Component
public class EmployeeDaoJdbcImpl implements EmployeeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select employee_id, first_name, last_name, email, salary, department_id from employee order by 2, 3";

    private static final String FIND_BY_ID =
            "select employee_id, first_name, last_name, email, salary, department_id " +
                    "from employee where employee_id = :employeeId";

    private static final String FIND_BY_DEPARTMENT_ID =
            "select employee_id, first_name, last_name, email, salary, department_id " +
                    "from employee where department_id = :departmentId";

    private final static String ADD_EMPLOYEE =
            "insert into employee (first_name, last_name, email, salary, department_id) " +
                    "values (:firstName, :lastName, :email, :salary, :departmentId)";

    private static final String UPDATE =
            "update employee set first_name = :firstName, last_name = :lastName, email = :email, " +
                    "salary = :salary, department_id = :departmentId where employee_id = :employeeId";

    private static final String DELETE =
            "delete from employee where employee_id = :employeeId";

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String EMPLOYEE_ID = "employeeId";

    public EmployeeDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        List<Employee> results = namedParameterJdbcTemplate.query(FIND_BY_DEPARTMENT_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return results;
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        List<Employee> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Employee add(Employee employee) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("firstName", employee.getFirstName());
        parameters.addValue("lastName", employee.getLastName());
        parameters.addValue("email", employee.getEmail());
        parameters.addValue("salary", employee.getSalary());
        parameters.addValue("departmentId", employee.getDepartmentId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_EMPLOYEE, parameters, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public void update(Employee employee) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(employee)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update employee in DB"));
    }

    @Override
    public void delete(Integer employeeId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(EMPLOYEE_ID, employeeId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete department from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

}