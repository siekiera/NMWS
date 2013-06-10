package pl.edu.pw.elka.maszyna.widok;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import pl.edu.pw.elka.maszyna.wspolne.DrzewoWnioskowania;
import pl.edu.pw.elka.maszyna.wspolne.DrzewoWnioskowania.WezelDrzewaWnioskowania;

public class OknoDrzewaWnioskowania extends JFrame {

	private final Dimension ROZMIAR = new Dimension(300, 400);
	private JTree kontrolkaDrzewaWnioskowania;
	private DrzewoWnioskowania drzewoWnioskowania;
	
	public OknoDrzewaWnioskowania(DrzewoWnioskowania drzewoWnioskowania) {
		this.drzewoWnioskowania = drzewoWnioskowania;
		initView();
		initListeners();
	}

	private void initView() {
		setPreferredSize(ROZMIAR);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Drzewo wnioskowania");
		
		
		//TODO dorobic to
		DefaultMutableTreeNode korzen = stworzWezly(drzewoWnioskowania.getKorzen());
//		stworzWezly(korzen);
		
		kontrolkaDrzewaWnioskowania = new JTree(korzen);
		kontrolkaDrzewaWnioskowania.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		JScrollPane widokDrzewa = new JScrollPane(kontrolkaDrzewaWnioskowania);
		
		add(widokDrzewa);
		
		pack();
		setVisible(true);
	}

	private DefaultMutableTreeNode stworzWezly(WezelDrzewaWnioskowania wezelDrzewaWnioskowania) {
		DefaultMutableTreeNode ojciec = new DefaultMutableTreeNode(wezelDrzewaWnioskowania.getKlauzula());
		if (wezelDrzewaWnioskowania.getDzieci() != null && !wezelDrzewaWnioskowania.getDzieci().isEmpty()) {
			for (WezelDrzewaWnioskowania wezelDziecka : wezelDrzewaWnioskowania.getDzieci()) {
				ojciec.add(stworzWezly(wezelDziecka));
			}
		}
		return ojciec;
	}

	private void initListeners() {
		// TODO Auto-generated method stub
		
	}
	
}
