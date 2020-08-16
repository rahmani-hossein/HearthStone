package Interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.Hero;
import model.Minion;
import model.spell;
import model.weapen;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Minion.class, name = "Minion"),
        @JsonSubTypes.Type(value = Hero.class, name = "hero"),


})
@JsonIgnoreProperties(ignoreUnknown = true)
public interface Attackable {

    public String BedeName();

    public void minusHealth(int minus);

    public void plusHealth(int plus);

}
