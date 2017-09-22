import spock.lang.Specification

/**
 * Created by luliru on 2017/9/21.
 */
class FirstSpec extends Specification {

    def "第一个feature"(){
        setup:
        def stack = new Stack()
        def elem = "push me"

        when:
        stack.push(elem)

        then:
        !stack.empty
        stack.size() == 1
        stack.peek() == elem
    }

    def "Exception Conditions"(){
        setup:
        def stack = new Stack()

        when:
        stack.pop()

        then:
        thrown(EmptyStackException)
        stack.empty
    }

    def "Exception Conditions2"(){
        setup:
        def stack = new Stack()

        when:
        stack.pop()

        then:
        def e = thrown(EmptyStackException)
        e.cause == null
    }

    def "Exception Conditions3"(){
        setup:
        def stack = new Stack()

        when:
        stack.pop()

        then:
        EmptyStackException e = thrown()
        e.cause == null
    }

    def "HashMap accepts null key"() {
        setup:
        def map = new HashMap()

        when:
        map.put(null, "elem")

        then:
        notThrown(NullPointerException)
    }

    def "Cleanup Blocks"(){
        setup:
        def file = new File("/some/path")
        file.createNewFile()

        // ...

        cleanup:
        file.delete()
    }

    def "computing the maximum of two numbers"() {
        expect:
        MathSpec.max(a, b) == c

        where:
        a << [5, 3]
        b << [1, 9]
        c << [5, 9]
    }
}
