package com.moo.suvankar.gxp.configs;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseVerifier {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger LOG = LoggerFactory
            .getLogger(DatabaseVerifier.class);

    @PostConstruct
    public void verifyTables() {
        try {
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM dictionary_entry", Integer.class);
            LOG.info("✅ Database tables exist.");
        } catch (Exception e) {
            LOG.error("❌ Table not found: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
