import org.pcap4j.core.PcapNetworkInterface
import org.pcap4j.packet.Packet
import org.pcap4j.util.NifSelector

fun main(){
    val nif=NifSelector().selectNetworkInterface()
    println("${nif.name}(${nif.description})")

    val handle=nif.openLive(65536,PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,10)
    handle.loop(5){
        packet: Packet? ->
        println(handle.timestamp)
        println(packet)
    }
    val s=handle.stats
    println("Recv:${s.numPacketsReceived}")
    println("Capt:${s.numPacketsCaptured}")
    println("Drop:${s.numPacketsDropped}")

}
