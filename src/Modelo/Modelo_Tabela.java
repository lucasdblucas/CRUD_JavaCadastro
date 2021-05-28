package Modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Modelo_Tabela extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;

    public Modelo_Tabela(ArrayList lin, String[] col) {
        this.linhas = lin;
        this.colunas = col;
    }

    public ArrayList getLinhas() {
        return linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public int getRowCount() {
        return linhas.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    // Expecifica o nome das colunas na tabela
    @Override
    public String getColumnName(int numCol) {
        return colunas[numCol];
    }

    // Retornas as informacoes na tabela
    public Object getValueAt(int numLinhas, int numColunas) {

        Object[] linha = (Object[]) getLinhas().get(numLinhas);

        return linha[numColunas];
    }

}
