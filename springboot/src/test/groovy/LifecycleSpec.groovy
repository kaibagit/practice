import spock.lang.Specification

/**
 * Created by luliru on 2017/9/22.
 */
class LifecycleSpec extends Specification{

    def setupSpec() {println "setupSpec"}     // run before the first feature method
    def cleanupSpec() {println "cleanupSpec"}   // run after the last feature method
    def setup() {println "setup"}          // run before every feature method
    def cleanup() {println "cleanup"}        // run after every feature method

    def "第一个feature"(){
        //setup, when, then, expect, cleanup, and where blocks.


        //given: is just an alias for setup
        setup:
        def map = new HashMap()

        when:
        map.put(null, "elem")

        then:
        notThrown(NullPointerException)

        cleanup:
        println "feture:cleanup"
    }

}
