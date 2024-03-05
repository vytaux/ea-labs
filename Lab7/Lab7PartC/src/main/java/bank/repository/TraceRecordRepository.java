package bank.repository;


import bank.domain.Account;
import bank.domain.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TraceRecordRepository extends JpaRepository<TraceRecord, Long> {

}




