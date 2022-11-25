package com.bodnar.dao.impl;

import com.bodnar.dao.PatrolBotDao;
import com.bodnar.domain.PatrolBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatrolBotDaoImpl implements PatrolBotDao {
    private static final String FIND_ALL = "SELECT * FROM  patrol_bot";
    private static final String CREATE = "INSERT patrol_bot(complete_set_id) VALUES (?)";
    private static final String UPDATE = "UPDATE patrol_bot SET complete_set_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM patrol_bot WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM patrol_bot WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PatrolBot> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(PatrolBot.class));
    }

    @Override
    public Optional<PatrolBot> findById(Integer patrolBotId) {
        Optional<PatrolBot> patrolBot;
        try {
            patrolBot = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(PatrolBot.class), patrolBotId));
        } catch (EmptyResultDataAccessException e) {
            patrolBot = Optional.empty();
        }
        return patrolBot;
    }

    @Override
    public int create(PatrolBot patrolBot) {
        return jdbcTemplate.update(CREATE, patrolBot.getCompleteSetId());
    }

    @Override
    public int update(Integer patrolBotId, PatrolBot patrolBot) {
        return jdbcTemplate.update(UPDATE, patrolBot.getCompleteSetId(), patrolBotId);
    }

    @Override
    public int delete(Integer patrolBotId) {
        return jdbcTemplate.update(DELETE, patrolBotId);
    }
}
