-- DROP TABLE employee;

CREATE TABLE employee
(
    id         bigserial PRIMARY KEY,
    "document" varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    "name"     varchar(255) NOT NULL,
    salary     float8       NULL
);

-- DROP TABLE project;

CREATE TABLE project
(
    id            bigserial PRIMARY KEY,
    creation_date timestamp    NOT NULL,
    "name"        varchar(255) NOT NULL
);

-- DROP TABLE public.employee_projects;

CREATE TABLE employee_projects
(
    employee_id int8 NOT NULL REFERENCES employee (id),
    projects_id int8 NOT NULL REFERENCES project (id),
    CONSTRAINT employee_projects_pkey PRIMARY KEY (employee_id, projects_id)
);
