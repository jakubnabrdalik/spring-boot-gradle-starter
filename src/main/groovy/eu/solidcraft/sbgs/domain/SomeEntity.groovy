package eu.solidcraft.sbgs.domain
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
class SomeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private BigDecimal someAmount;

    @NotNull
    private Date someDate;

    SomeEntity(String username, BigDecimal someAmount, Date someDate) {
        this.username = username
        this.someAmount = someAmount
        this.someDate = someDate
    }

    Long getId() {
        return id
    }

    String getUsername() {
        return username
    }

    BigDecimal getSomeAmount() {
        return someAmount
    }

    Date getSomeDate() {
        return someDate
    }
}
