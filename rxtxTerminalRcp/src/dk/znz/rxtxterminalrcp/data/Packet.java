package dk.znz.rxtxterminalrcp.data;

import java.util.concurrent.atomic.AtomicLong;

public class Packet {
  private long     mNumber;
  private long     mTime;
  private Address  mSource;
  private Address  mDestination;
  private Protocol mProtocol;
  private Info     mInfo;
  
  private static AtomicLong Counter = new AtomicLong(0);

  public Packet() {
    this(new Address(), new Address(), new Protocol(), new Info());
  }
  
  public Packet(Address source, Address destination, Protocol protocol, Info info) {
    mNumber = Counter.incrementAndGet();
    mTime = System.currentTimeMillis();
    mSource = source;
    mDestination = destination;
    mProtocol = protocol;
    mInfo = info;
  }

  public long getNumber() {
    return mNumber;
  }

  public void setNumber(long number) {
    mNumber = number;
  }

  public long getTime() {
    return mTime;
  }

  public void setTime(long time) {
    mTime = time;
  }

  public Address getSource() {
    return mSource;
  }

  public void setSource(Address source) {
    mSource = source;
  }

  public Address getDestination() {
    return mDestination;
  }

  public void setDestination(Address destination) {
    mDestination = destination;
  }

  public Protocol getProtocol() {
    return mProtocol;
  }

  public void setProtocol(Protocol protocol) {
    mProtocol = protocol;
  }

  public Info getInfo() {
    return mInfo;
  }

  public void setInfo(Info info) {
    mInfo = info;
  }
}
