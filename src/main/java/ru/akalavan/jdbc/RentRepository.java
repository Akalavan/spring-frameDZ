package ru.akalavan.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.akalavan.objects.RentJdbcRelease;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RentRepository {

    private final JdbcTemplate jdbcTemplate;

    public RentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RentJdbcRelease> getRentHigher(int price) {
        return jdbcTemplate.query(String.format("select * from rent where price > %s", price), new RowMapper<RentJdbcRelease>() {
            @Override
            public RentJdbcRelease mapRow(ResultSet rs, int i) throws SQLException {
                return new RentJdbcRelease(
                        rs.getLong("id"),
                        rs.getInt("price"),
                        rs.getDate("date_from"),
                        rs.getDate("date_to"),
                        rs.getInt("rent_car")
                );
            }
        });
    }

    public int count() { return jdbcTemplate.queryForObject("select count(*) from rent", Integer.class); }
}
