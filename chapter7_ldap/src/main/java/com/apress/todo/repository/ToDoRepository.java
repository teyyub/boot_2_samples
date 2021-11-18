package com.apress.todo.repository;

import com.apress.todo.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {
//    private static final String SQL_INSERT = "insert into todo (id, description, created, modified, completed) values (:id,:description,:created,:modified,:completed)";
//    private static final String SQL_QUERY_FIND_ALL = "select id, description, created, modified, completed from todo";
//    private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " where id = :id";
//    private static final String SQL_UPDATE = "update todo set description = :description, modified = :modified, completed = :completed where id = :id";
//    private static final String SQL_DELETE = "delete from todo where id = :id";
//    private final NamedParameterJdbcTemplate jdbcTemplate;
//
//    public ToDoRepository(NamedParameterJdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    private RowMapper<Optional<ToDoDto>> toDoRowMapper = (ResultSet rs, int rowNum) -> {
//        ToDoDto toDoDto = new ToDoDto();
//        toDoDto.setId(rs.getString("id"));
//        toDoDto.setDescription(rs.getString("description"));
////        toDoDto.setModified(rs.getTimestamp("modified").toLocalDateTime());
////        toDoDto.setCreated(rs.getTimestamp("created").toLocalDateTime());
////        toDoDto.setCompleted(rs.getBoolean("completed"));
//        return Optional.of(toDoDto);
//    };
//
//    @Override
//    public Optional<ToDoDto> save(ToDoDto domain) {
//        ToDoDto result = findById(Long.valueOf(domain.getId())).get();
//        if(result != null){
//            result.setDescription(domain.getDescription());
////            result.setCompleted(domain.isCompleted());
////            result.setModified(LocalDateTime.now());
//            return upsert(result, SQL_UPDATE);
//        }
//        return upsert(domain,SQL_INSERT);
//    }
//
//    @Override
//    public Iterable<Optional<ToDoDto>> save(Collection<ToDoDto> domains) {
//        domains.forEach( this::save);
//        return findAll();
//    }
//
//    @Override
//    public void delete(final ToDoDto domain) {
//        Map<String, Long> namedParameters = Collections.singletonMap("id", Long.valueOf(domain.getId()));
//        this.jdbcTemplate.update(SQL_DELETE,namedParameters);
//    }
//
//    @Override
//    public Optional<ToDoDto> findById(Long id) {
//        try {
//            Map<String, Long> namedParameters = Collections.singletonMap("id", id);
//            return this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID,
//                    namedParameters, toDoRowMapper);
//        } catch (EmptyResultDataAccessException ex) {
//            return null;
//        }
//    }
//
//    @Override
//    public Iterable<Optional<ToDoDto>> findAll() {
//        return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, toDoRowMapper);
//    }
//
//    private Optional<ToDoDto> upsert(final ToDoDto toDo, final String sql){
//        Map<String, Object> namedParameters = new HashMap<>();
//        namedParameters.put("id",toDo.getId());
//        namedParameters.put("description",toDo.getDescription());
////        namedParameters.put("created",java.sql.Timestamp.valueOf(toDo.
////                getCreated()));
////        namedParameters.put("modified",java.sql.Timestamp.valueOf(toDo.
////                getModified()));
////        namedParameters.put("completed",toDo.isCompleted());
//        this.jdbcTemplate.update(sql,namedParameters);
//        return findById(Long.valueOf(toDo.getId()));
//    }
//
//    private ToDo dtoConvert(ToDoDto dto){
//        return dtoConvert(dto , new ToDo());
//    }
//    private ToDo dtoConvert(ToDoDto dto , ToDo entity){
//        entity.setDescription(dto.getDescription());
//        return entity;
//    }
//
//    private ToDoDto entityConvert(ToDo entity){
//        return entityConvert(entity, new ToDoDto());
//    }
//    private ToDoDto entityConvert(ToDo entity, ToDoDto dto){
//        dto.setId(entity.getId().toString());
//        dto.setDescription(entity.getDescription());
//        return dto;
//    }
}
