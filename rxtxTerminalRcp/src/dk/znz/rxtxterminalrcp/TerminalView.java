package dk.znz.rxtxterminalrcp;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;
import swing2swt.layout.FlowLayout;
import dk.znz.rxtxterminalrcp.data.Packet;

public class TerminalView extends ViewPart {

  public static final String ID            = "dk.znz.rxtxterminalrcp.views.terminalview";

  private PacketRender       mPacketRender = new PacketRender();
  private TableViewer        mTableViewer;
  private Table              mTable;
  private WritableList       mPackets;
  private DataBindingContext mBindingContext;

  public TerminalView() {

  }

  @Override public void createPartControl(Composite parent) {
    parent.setLayout(new BorderLayout(0, 0));

    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(BorderLayout.SOUTH);
    composite.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    Button cdRadioButton = new Button(composite, SWT.RADIO);
    cdRadioButton.setText("CD");
    cdRadioButton.setEnabled(false);
    Button ctsRadioButton = new Button(composite, SWT.RADIO);
    ctsRadioButton.setText("CTS");
    ctsRadioButton.setEnabled(false);
    Button dsrRadioButton = new Button(composite, SWT.RADIO);
    dsrRadioButton.setText("DSR");
    dsrRadioButton.setEnabled(false);
    Button riRadioButton = new Button(composite, SWT.RADIO);
    riRadioButton.setText("RI");
    riRadioButton.setEnabled(false);

    Button dtrCheckButton = new Button(composite, SWT.CHECK);
    dtrCheckButton.setText("DTR");
    Button rtsCheckButton = new Button(composite, SWT.CHECK);
    rtsCheckButton.setText("RTS");

    Button btnAdd = new Button(composite, SWT.NONE);
    btnAdd.addSelectionListener(new SelectionAdapter() {
      @Override public void widgetSelected(SelectionEvent e) {
        mPackets.add(new Packet());
      }
    });
    btnAdd.setText("Add");

    mTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
    mTable = mTableViewer.getTable();
    mTable.setLayoutData(BorderLayout.CENTER);
    mTable.setHeaderVisible(true);
    mTable.setLinesVisible(true);

    mPackets = new WritableList();
    mPackets.add(new Packet());

//    Realm.runWithDefault(Realm.getDefault(), new Runnable() {
//      @Override public void run() {
//        try {
//          while(true) {
//            Thread.sleep(500);
//            Packet packet = new Packet();
//            mWritableList.add(packet);
//          }
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//      }
//    });

    TableColumn numberColumn = new TableColumn(mTable, SWT.NONE);
    numberColumn.setWidth(31);
    numberColumn.setText("No.");

    TableColumn timeColumn = new TableColumn(mTable, SWT.NONE);
    timeColumn.setWidth(146);
    timeColumn.setText("Time");

    TableColumn sourceColumn = new TableColumn(mTable, SWT.NONE);
    sourceColumn.setWidth(100);
    sourceColumn.setText("Source");

    TableColumn destinationColumn = new TableColumn(mTable, SWT.NONE);
    destinationColumn.setWidth(100);
    destinationColumn.setText("Destination");

    TableColumn protocolColumn = new TableColumn(mTable, SWT.NONE);
    protocolColumn.setWidth(100);
    protocolColumn.setText("Protocol");

    TableColumn infoColumn = new TableColumn(mTable, SWT.NONE);
    infoColumn.setWidth(100);
    infoColumn.setText("Info");
    mBindingContext = initDataBindings();

  }

  @Override public void setFocus() {
    mTable.setFocus();
  }

  protected DataBindingContext initDataBindings() {
    DataBindingContext bindingContext = new DataBindingContext();
    //
    ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
    mTableViewer.setContentProvider(listContentProvider);
    //

    IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Packet.class, new String[] { "number", "time", "source", "destination", "protocol", "info" });
    mTableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps) {
      @Override public String getColumnText(Object element, int columnIndex) {
        return mPacketRender.getLabel((Packet) element, columnIndex);
      }
    });
    //
    mTableViewer.setInput(mPackets);
    //
    return bindingContext;
  }
}
