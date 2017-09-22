import spock.lang.Specification

class Publisher {
    List<Subscriber> subscribers = []
    int messageCount = 0
    void send(String message){
        subscribers*.receive(message)
        messageCount++
    }
}

interface Subscriber {
    void receive(String message)
}

class PublisherSpec extends Specification {
    Publisher publisher = new Publisher()

    def subscriber = Mock(Subscriber)
    def subscriber2 = Mock(Subscriber)

    def setup() {
        publisher.subscribers << subscriber // << is a Groovy shorthand for List.add()
        publisher.subscribers << subscriber2
    }

    def "should send messages to all subscribers"() {
        when:
        publisher.send("hello")

        then:
        1 * subscriber.receive("hello")
        1 * subscriber2.receive("hello")
    }
}