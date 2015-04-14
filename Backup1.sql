--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.6
-- Dumped by pg_dump version 9.3.6
-- Started on 2015-04-08 17:28:31

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE "UsersDB";
--
-- TOC entry 1936 (class 1262 OID 16393)
-- Name: UsersDB; Type: DATABASE; Schema: -; Owner: admin
--

CREATE DATABASE "UsersDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1251' LC_CTYPE = 'Ukrainian_Ukraine.1251';


ALTER DATABASE "UsersDB" OWNER TO admin;

\connect "UsersDB"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 1937 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 171 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1939 (class 0 OID 0)
-- Dependencies: 171
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 16394)
-- Name: Users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Users" (
    nickname character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    userrole character varying(10) NOT NULL,
    name character varying(20) NOT NULL,
    surname character varying(20) NOT NULL,
    sex character varying(10),
    email character varying(30),
    phone character varying(20) NOT NULL,
    address character varying(50)
);


ALTER TABLE public."Users" OWNER TO postgres;

--
-- TOC entry 1931 (class 0 OID 16394)
-- Dependencies: 170
-- Data for Name: Users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Users" (nickname, password, userrole, name, surname, sex, email, phone, address) FROM stdin;
admin2	admin2	Admin	adasdasd	ad123	male	adasdte	23434235	ad
qwe	qwe	Admin	qwe	qwe	\N	qwe	23	\N
first	aaa	User	asd	ddd	female	sdsad	768678	asd
qe	qwe	Admin	wqe	qwe	male	qwe	qwe	\N
ewr	wer	Admin	wer	wer	\N	\N	wre	\N
admin	admin	Admin	Steve	Mattew	male	admin@org.com	0563585584	Dnepropetrovsk,
admin 	admin	Admin	admin	admin	male	some@some.com	25454545	Some adress
\.


--
-- TOC entry 1823 (class 2606 OID 16398)
-- Name: nickname; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Users"
    ADD CONSTRAINT nickname PRIMARY KEY (nickname);


--
-- TOC entry 1938 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 1940 (class 0 OID 0)
-- Dependencies: 170
-- Name: Users; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE "Users" FROM PUBLIC;
REVOKE ALL ON TABLE "Users" FROM postgres;
GRANT ALL ON TABLE "Users" TO postgres;
GRANT ALL ON TABLE "Users" TO PUBLIC;


--
-- TOC entry 1490 (class 826 OID 16400)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON TABLES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON TABLES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO PUBLIC;


-- Completed on 2015-04-08 17:28:31

--
-- PostgreSQL database dump complete
--

