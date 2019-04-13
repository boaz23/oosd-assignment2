package polycalc;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolynomialBuilder {
    private List<PolyTerm> polyTerms;
    private Map<Integer, Pair<Integer, PolyTerm>> polyTermsMap;

    public PolynomialBuilder() {
        this.polyTerms = new ArrayList<>();
        this.polyTermsMap = new HashMap<>();
    }
    public PolynomialBuilder(int expectedCapacity) {
        this.polyTerms = new ArrayList<>(expectedCapacity);
        this.polyTermsMap = new HashMap<>(expectedCapacity);
    }

    public void addPolyTerm(PolyTerm polyTerm) {
        if (!this.polyTermsMap.containsKey(polyTerm.getExponent())) {
            // we haven't seen this exponent
            int index = this.polyTerms.size();
            this.polyTerms.add(polyTerm);
            this.polyTermsMap.put(polyTerm.getExponent(), new Pair<>(index, polyTerm));
        }
        else {
            // we have seen this exponent
            Pair<Integer, PolyTerm> polyTermPair = this.polyTermsMap.get(polyTerm.getExponent());
            PolyTerm otherPolyTerm = polyTermPair.getValue();
            PolyTerm resultPolyTerm = polyTerm.add(otherPolyTerm);

            this.polyTerms.set(polyTermPair.getKey(), resultPolyTerm);
            this.polyTermsMap.put(polyTerm.getExponent(), new Pair<>(polyTermPair.getKey(), resultPolyTerm));
        }
    }

    public Polynomial build() {
        return new Polynomial(this.polyTerms);
    }
}
