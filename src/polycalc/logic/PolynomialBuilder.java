package polycalc.logic;

import javafx.util.Pair;
import polycalc.utils.ScalarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolynomialBuilder {
    // The list of all terms, used for the instantiation
    private List<PolyTerm> polyTerms;

    // store the terms in a hashtable
    // the key is the exponent
    // for each term, attach it's index in the list (polyTerms)
    // for fast update of the same exponent
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
        int exponent = polyTerm.getExponent();

        // check whether a poly term of this exponent has already been added.
        // if so, add them together and update
        if (!this.polyTermsMap.containsKey(exponent)) {
            // poly term with a coefficient of zero are not allowed
            if (!ScalarUtils.isZero(polyTerm.getCoefficient())) {
                int index = this.polyTerms.size();
                this.polyTerms.add(polyTerm);
                this.polyTermsMap.put(exponent, new Pair<>(index, polyTerm));
            }
        }
        else {
            Pair<Integer, PolyTerm> polyTermPair = this.polyTermsMap.get(exponent);
            int listIndex = polyTermPair.getKey();
            PolyTerm otherPolyTerm = polyTermPair.getValue();

            PolyTerm resultPolyTerm = polyTerm.add(otherPolyTerm);
            this.polyTerms.set(listIndex, resultPolyTerm);
            this.polyTermsMap.put(exponent, new Pair<>(listIndex, resultPolyTerm));
        }
    }

    public Polynomial build() {
        return new Polynomial(this.polyTerms);
    }
}
