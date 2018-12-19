

CREATE TABLE produit
(
    id bigint NOT NULL,
    description character varying(255),
    titre character varying(255) ,
    CONSTRAINT produit_pkey PRIMARY KEY (id)
);



CREATE TABLE client
(
    id bigint NOT NULL,
    categorie character varying(255),
    date_naissance timestamp without time zone,
    email character varying(255),
    nom character varying(255),
    prenom character varying(255) NOT NULL,
    CONSTRAINT client_pkey PRIMARY KEY (id)
);



CREATE TABLE client_produits
(
    client_id bigint NOT NULL,
    produits_id bigint NOT NULL,
    CONSTRAINT client_produits_pkey PRIMARY KEY (client_id, produits_id),
    CONSTRAINT uk_4w4q0mo8ev77xpa37job1do2g UNIQUE (produits_id),
    CONSTRAINT fk21rkfqi169593nsdln3iq39ot FOREIGN KEY (produits_id)
        REFERENCES public.produit (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

