package co.edu.unal.software_engineering.meetu.model;

import co.edu.unal.software_engineering.meetu.log.LogModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "comment", schema = "public")
public class Comment extends LogModel {

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */
    @Id
    @SequenceGenerator(name = "COMMENT_COMMENTID_GENERATOR", sequenceName = "public.comment_comment_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "COMMENT_COMMENTID_GENERATOR", strategy = GenerationType.SEQUENCE)

    @Column(name = "comment_id")
    private Integer comment_id;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "date")
    private Date date;

    // bi-directional many-to-one association to Plan
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;


    /**
     * Constructors
     */
    public Comment() {
    }


    /**
     * Getters and Setters
     */
    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }


    /**
     * Methods
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Comment))
            return false;
        return comment_id.equals(((Comment) object).getComment_id());
    }

    @Override
    public int hashCode() {
        return comment_id;
    }
}