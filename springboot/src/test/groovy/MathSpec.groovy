import spock.lang.Specification

/**
 * Created by luliru on 2017/9/19.
 */
class MathSpec extends Specification {

    def "maximum of two numbers"() {
        expect:
        // exercise math method for a few different inputs
        Math.max(1, 3) == 3
        Math.max(7, 4) == 7
        Math.max(0, 0) == 0
    }

    def "maximum of two numbers"(int a, int b, int c) {
        expect:
        MathSpec.max(a, b) == c

        where:
        a | b | c
        1 | 3 | 3   // passes
        7 | 4 | 4   // fails
        0 | 0 | 0   // passes
    }

    def "maximum of two numbers 2"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }
}