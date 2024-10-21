
import static org.junit.jupiter.api.Assertions.*;


import org.epower.model.Customer;
import org.epower.model.Report;
import org.epower.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ReportTest {

    private Report report;
    private Customer customer;

    @BeforeEach
    void setUp() {
        report = new Report();
        customer = new Customer("user123", "John Doe");
        customer.setBalance(100.0);

        // Simulate transactions
        Transaction transaction1 = new Transaction("T1001", "CS1001", "AC", 0.30);
        transaction1.setEnergyConsumed(10);
        customer.startTransaction(transaction1);
        customer.endTransaction(transaction1);

        Transaction transaction2 = new Transaction("T1002", "CS1002", "DC", 0.50);
        transaction2.setEnergyConsumed(5);
        customer.startTransaction(transaction2);
        customer.endTransaction(transaction2);
    }

    @Test
    void testGenerateCustomerReport() {
        List<Transaction> transactions = report.generateCustomerReport(customer);
        assertEquals(2, transactions.size());
    }

    @Test
    void testCalculateTotalEnergyConsumed() {
        double totalEnergy = report.calculateTotalEnergyConsumed(customer.getTransactionHistory());
        assertEquals(15, totalEnergy);
    }

    @Test
    void testCalculateTotalAmountCharged() {
        double totalAmount = report.calculateTotalAmountCharged(customer.getTransactionHistory());
        assertEquals(7.5, totalAmount);  // (10 * 0.30) + (5 * 0.50)
    }
}