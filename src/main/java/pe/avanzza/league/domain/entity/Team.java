package pe.avanzza.league.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import pe.avanzza.league.domain.vo.AuditModel;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
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
@Table(name = "team")
@JsonIgnoreProperties({"leagues", "players"})
public class Team extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name", length = 100, nullable = false)
    private String teamName;

    @Column(name = "pass_number")
    private int passNumber;

    @Column(name = "fault_number")
    private int faultNumber;

    @ManyToMany(mappedBy = "teams", cascade = CascadeType.PERSIST)
    @XmlTransient
    @JsonIgnore
    private List<League> leagues;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
