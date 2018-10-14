package com.thorton.grant.uspto.prototypewebapp.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "Patents")
public class Patent extends BaseEntity {

        private String PTOpatentID;
        private String description;
        private String industryID;
        private String category;

        @ManyToOne
        private PTOUser patentOwner;

        public String getPTOpatentID() {
                return PTOpatentID;
        }

        public void setPTOpatentID(String PTOpatentID) {
                this.PTOpatentID = PTOpatentID;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getIndustryID() {
                return industryID;
        }

        public void setIndustryID(String industryID) {
                this.industryID = industryID;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public PTOUser getPatentOwner() {
                return patentOwner;
        }

        public void setPatentOwner(PTOUser patentOwner) {
                this.patentOwner = patentOwner;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Patent patent = (Patent) o;
                return Objects.equals(PTOpatentID, patent.PTOpatentID) &&
                        Objects.equals(description, patent.description) &&
                        Objects.equals(industryID, patent.industryID) &&
                        Objects.equals(category, patent.category) &&
                        Objects.equals(patentOwner, patent.patentOwner);
        }

        @Override
        public int hashCode() {
                return Objects.hash(PTOpatentID, description, industryID, category, patentOwner);
        }

        @Override
        public String toString() {
                return "Patent{" +
                        "PTOpatentID='" + PTOpatentID + '\'' +
                        ", description='" + description + '\'' +
                        ", industryID='" + industryID + '\'' +
                        ", category='" + category + '\'' +
                        ", patentOwner=" + patentOwner +
                        '}';
        }
}
