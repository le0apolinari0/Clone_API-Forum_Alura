create table resposta(
    id bigint not null auto_increment,
    mensagem varchar(300) not null,
    data_criacao datetime not null,
    topico_id bigint not null,
    autor_id bigint not null,
    soulucao int(1) not null,
    respostas varchar(300),

    primary key(id),
    foreign key (topico_id) references topico(id),
    foreign key (autor_id) references usuario(id)
);