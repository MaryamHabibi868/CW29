package ir.maktab.cw29.repository;

import ir.maktab.cw29.document.Log;
import ir.maktab.cw29.dto.RequestsPerUserDto;
import ir.maktab.cw29.dto.UserRequestPerStatusDto;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@Repository
public class LogRepositoryCustomImpl implements LogRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public LogRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<RequestsPerUserDto> findRequestNumbersPerUser(){
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("userId").count().as("numberOfRequest"));
        return mongoTemplate.aggregate(aggregation, Log.class, RequestsPerUserDto.class).getMappedResults();
    }

    public List<UserRequestPerStatusDto> findRequestsByUser(Long userId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("userId").is(userId)
                ), Aggregation.group("status").count().as("numberOfRequest")
        );
        return mongoTemplate.aggregate(aggregation, Log.class, UserRequestPerStatusDto.class).getMappedResults();

    }
}
