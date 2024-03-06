package bank.repository;


import bank.domain.Account;
import bank.domain.TraceRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TraceRecordRepository extends JpaRepository<TraceRecord, Long> {
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    default void saveTraceRecord(TraceRecord traceRecord) {
        save(traceRecord);
    }
}




