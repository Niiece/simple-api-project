package org.example.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonIgnoreProperties(value = {"_id", "__v"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    private String id;
    private String username;
    private String email;
    private String name;
    private String surname;

    @JsonProperty("currency_code")
    private String currencyCode;


    @JsonSetter("id")
    public void setId(Object idValue) {
        if (idValue instanceof Integer) {
            this.id = String.valueOf(idValue);
        } else if (idValue instanceof String) {
            this.id = (String) idValue;
        } else {
            throw new RuntimeException("Unsupported field type");
        }
    }

    @JsonGetter("id")
    public Object getIdJson() {
        if (id != null && id.matches("\\d+")) {
            return Integer.valueOf(id);
        }
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;

        return new EqualsBuilder().append(username, player.username)
            .append(email, player.email)
            .append(name, player.name)
            .append(surname, player.surname)
            .append(currencyCode, player.currencyCode)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(username)
            .append(email)
            .append(name)
            .append(surname)
            .append(currencyCode)
            .toHashCode();
    }
}
