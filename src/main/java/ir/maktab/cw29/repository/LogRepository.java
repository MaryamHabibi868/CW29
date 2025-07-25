package ir.maktab.cw29.repository;

import ir.maktab.cw29.document.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log, Long> {
}
