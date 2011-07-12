package dk.znz.rxtxterminalrcp;

import java.text.DateFormat;
import java.util.Date;

import dk.znz.rxtxterminalrcp.data.Packet;

public class PacketRender {
  private static final int     COLUMN_NUMBER       = 0;
  private static final int     COLUMN_TIME         = 1;
  private static final int     COLUMN_SOURCE       = 2;
  private static final int     COLUMN_DESTINATION  = 3;
  private static final int     COLUMN_PROTOCOL     = 4;
  private static final int     COLUMN_INFO         = 5;
  private PacketColumnRender[] mPacketColumnRenders = new PacketColumnRender[6];

  public PacketRender() {
    mPacketColumnRenders[COLUMN_NUMBER] = new PacketColumnRender() {
      @Override public String getLabel(Packet packet) {
        return Long.toString(packet.getNumber());
      }
    };
    mPacketColumnRenders[COLUMN_TIME] = new PacketColumnRender() {
      @Override public String getLabel(Packet packet) {
        return DateFormat.getDateTimeInstance().format(new Date(packet.getTime()));
      }
    };
    mPacketColumnRenders[COLUMN_SOURCE] = new PacketColumnRender() {
      @Override public String getLabel(Packet packet) {
        return packet.getSource().toString();
      }
    };
    mPacketColumnRenders[COLUMN_DESTINATION] = new PacketColumnRender() {
      @Override public String getLabel(Packet packet) {
        return packet.getDestination().toString();
      }
    };
    mPacketColumnRenders[COLUMN_PROTOCOL] = new PacketColumnRender() {
      @Override public String getLabel(Packet packet) {
        return packet.getProtocol().toString();
      }
    };
    mPacketColumnRenders[COLUMN_INFO] = new PacketColumnRender() {
      @Override public String getLabel(Packet packet) {
        return packet.getInfo().toString();
      }
    };
  }
  
  public String getLabel(Packet packet, int index) {
    return mPacketColumnRenders[index].getLabel(packet);
  }

  interface PacketColumnRender {
    String getLabel(Packet packet);
  }
}
