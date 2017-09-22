import spock.lang.Specification

/**
 * Created by luliru on 2017/9/19.
 */
class TestAccount extends Specification {
    def "withdraw from account" () {
        given:
        def account = new Account(500.00)

        when:
        account.withdraw(200.00)

        then:
        account.balance == 300.00
    }
}