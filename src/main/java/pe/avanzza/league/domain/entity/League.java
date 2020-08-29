package pe.avanzza.league.domain.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import pe.avanzza.league.domain.vo.AuditModel;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Gloria R. Leyva Jerez
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "league")
public class League extends AuditModel {

    private static final long serialVersionUID = -4321905888256941403L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private int leagueId;

    @Column(name = "league_name", length = 100, nullable = false)
    private String leagueName;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "game_number", nullable = false)
    private int gameNumber;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "league_team", joinColumns = {
            @JoinColumn(name = "league_id", referencedColumnName = "league_id")}, inverseJoinColumns = {
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")})
    @XmlTransient
    @JsonIgnore
    private List<Team> teams;
}
