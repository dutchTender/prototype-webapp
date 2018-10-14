package com.thorton.grant.uspto.prototypewebapp.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Patents")
public class Patent extends BaseEntity {

        private String PTOpatentID;
        private String description;
        private String industryID;
        private String category;

        @ManyToOne
        private PTOUser patentOwner;

}
