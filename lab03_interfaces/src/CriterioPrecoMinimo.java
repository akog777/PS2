public class CriterioPrecoMinimo implements CriterioBusca {
    public boolean testar(Produto p, String valor) {
        double precoMinimo = Double.parseDouble(valor);
        return p.getPreco() >= precoMinimo;
    }

}
