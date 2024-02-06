CREATE TABLE public."CoordinateMonitoraggio" (
    "Geo_id" numeric(10,0) DEFAULT '0'::numeric NOT NULL,
    "Latitudine" double precision DEFAULT '0'::numeric NOT NULL,
    "Longitudine" double precision DEFAULT '0'::numeric NOT NULL,
    "Denominazione" character(100) DEFAULT 'empty'::bpchar NOT NULL,
    "Stato" character(100) DEFAULT 'empty'::bpchar NOT NULL,
    "CountryCode" character(3) NOT NULL
);

ALTER TABLE IF EXISTS public."CoordinateMonitoraggio"
    ADD PRIMARY KEY ("Geo_id");

CREATE TABLE public.address (
    via character(100) NOT NULL,
    civico numeric(3,0) DEFAULT '0'::numeric NOT NULL,
    cap numeric(5,0) DEFAULT '0'::numeric NOT NULL,
    comune character(100) NOT NULL,
    provincia character(2) NOT NULL,
    "id_A" integer NOT NULL
);

ALTER TABLE public.address ALTER COLUMN "id_A" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."address_id_A_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

ALTER TABLE IF EXISTS public.address
    ADD PRIMARY KEY ("id_A");

CREATE TABLE public."centriMonitoraggio" (
    nome character(100) NOT NULL,
    "areaInteresse" numeric(10,0) DEFAULT '0'::numeric NOT NULL
);

ALTER TABLE IF EXISTS public."centriMonitoraggio"
    ADD CONSTRAINT "centriMonitoraggio_pkey" PRIMARY KEY (nome);

CREATE TABLE public."indirizzoCentri" (
    name character(100) DEFAULT '0'::bpchar NOT NULL,
    id integer DEFAULT '0'::numeric NOT NULL
);

ALTER TABLE IF EXISTS public."indirizzoCentri"
    ADD PRIMARY KEY (name, id);
ALTER TABLE IF EXISTS public."indirizzoCentri"
    ADD CONSTRAINT centri FOREIGN KEY (name)
    REFERENCES public."centriMonitoraggio" (nome) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public."indirizzoCentri"
    ADD CONSTRAINT indirizzo FOREIGN KEY (id)
    REFERENCES public.address ("id_A") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public."indirizzoCentri"
    ADD CONSTRAINT indirizzo FOREIGN KEY (id)
    REFERENCES public.address ("id_A") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

CREATE TABLE public."operatoriRegistrati" (
    "CF" character(100) NOT NULL,
    name character(100) NOT NULL,
    surname character(100) NOT NULL,
    mail character(100) NOT NULL,
    userid character(100) NOT NULL,
    password character(100) NOT NULL,
    "nameCM" character(100) NOT NULL
);

ALTER TABLE IF EXISTS public."operatoriRegistrati"
    ADD PRIMARY KEY ("CF");
ALTER TABLE IF EXISTS public."operatoriRegistrati"
    ADD CONSTRAINT centro FOREIGN KEY ("nameCM")
    REFERENCES public."centriMonitoraggio" (nome) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

CREATE TABLE public."parametriClimatici" (
    "nameCM" character(100) NOT NULL,
    "interestedArea" numeric(10,0) NOT NULL,
    "dataRilevazione" date NOT NULL,
    "climateCategory" character(100) NOT NULL,
    explanation character(100) NOT NULL,
    score numeric(1,0) DEFAULT '1'::numeric NOT NULL,
    notes character(256) DEFAULT 'empty'::bpchar NOT NULL
);

ALTER TABLE IF EXISTS public."parametriClimatici"
    ADD PRIMARY KEY ("nameCM", "interestedArea");
ALTER TABLE IF EXISTS public."parametriClimatici"
    ADD CONSTRAINT centro FOREIGN KEY ("nameCM")
    REFERENCES public."centriMonitoraggio" (nome) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public."parametriClimatici"
    ADD CONSTRAINT "areaDiInteresse" FOREIGN KEY ("interestedArea")
    REFERENCES public."CoordinateMonitoraggio" ("Geo_id") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;