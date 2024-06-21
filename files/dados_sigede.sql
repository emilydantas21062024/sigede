PGDMP  "    8                |            sigede    14.5    16.1 )                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            !           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            "           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            #           1262    16396    sigede    DATABASE     h   CREATE DATABASE sigede WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE sigede;
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            $           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4            �            1259    16447    billing_seq    SEQUENCE     u   CREATE SEQUENCE public.billing_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.billing_seq;
       public          postgres    false    4            �            1259    16599    boleto    TABLE     v  CREATE TABLE public.boleto (
    id bigint NOT NULL,
    cpf_cnpj_cliente character varying(255),
    data_pagamento character varying(255),
    data_vencimento character varying(255),
    desconto double precision,
    descricao character varying(255),
    dias_atraso integer,
    juros double precision,
    metodo_pagamento character varying(255),
    multa double precision,
    nome_cliente character varying(255),
    numero character varying(255),
    observacao character varying(255),
    parcela character varying(255),
    status character varying(255),
    valor double precision,
    valor_final double precision
);
    DROP TABLE public.boleto;
       public         heap    postgres    false    4            �            1259    16627 
   boleto_seq    SEQUENCE     t   CREATE SEQUENCE public.boleto_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.boleto_seq;
       public          postgres    false    4            �            1259    16578 
   charge_seq    SEQUENCE     t   CREATE SEQUENCE public.charge_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.charge_seq;
       public          postgres    false    4            �            1259    16606    cliente    TABLE     |  CREATE TABLE public.cliente (
    id bigint NOT NULL,
    cep character varying(255),
    cidade character varying(255),
    cpf_cnpj character varying(255),
    data_nascimento character varying(255),
    email character varying(255),
    endereco character varying(255),
    estado character varying(255),
    nome character varying(255),
    telefone character varying(255)
);
    DROP TABLE public.cliente;
       public         heap    postgres    false    4            �            1259    16628    cliente_seq    SEQUENCE     u   CREATE SEQUENCE public.cliente_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.cliente_seq;
       public          postgres    false    4            �            1259    16613    cobranca    TABLE     �   CREATE TABLE public.cobranca (
    id bigint NOT NULL,
    data character varying(255),
    descricao character varying(255),
    valor double precision,
    boleto_id bigint
);
    DROP TABLE public.cobranca;
       public         heap    postgres    false    4            �            1259    16629    cobranca_seq    SEQUENCE     v   CREATE SEQUENCE public.cobranca_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.cobranca_seq;
       public          postgres    false    4            �            1259    16405    customer_seq    SEQUENCE     v   CREATE SEQUENCE public.customer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.customer_seq;
       public          postgres    false    4            �            1259    16412    debt_seq    SEQUENCE     r   CREATE SEQUENCE public.debt_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.debt_seq;
       public          postgres    false    4            �            1259    16406    user_seq    SEQUENCE     r   CREATE SEQUENCE public.user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.user_seq;
       public          postgres    false    4            �            1259    16619    usuario    TABLE       CREATE TABLE public.usuario (
    id bigint NOT NULL,
    login character varying(255) NOT NULL,
    papel character varying(255) NOT NULL,
    primeiro_nome character varying(255) NOT NULL,
    senha character varying(255) NOT NULL,
    ultimo_nome character varying(255) NOT NULL
);
    DROP TABLE public.usuario;
       public         heap    postgres    false    4            �            1259    16618    usuario_id_seq    SEQUENCE     w   CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.usuario_id_seq;
       public          postgres    false    4    219            %           0    0    usuario_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;
          public          postgres    false    218            �            1259    16436 	   uuser_seq    SEQUENCE     s   CREATE SEQUENCE public.uuser_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.uuser_seq;
       public          postgres    false    4            {           2604    16622 
   usuario id    DEFAULT     h   ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);
 9   ALTER TABLE public.usuario ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219                      0    16599    boleto 
   TABLE DATA           �   COPY public.boleto (id, cpf_cnpj_cliente, data_pagamento, data_vencimento, desconto, descricao, dias_atraso, juros, metodo_pagamento, multa, nome_cliente, numero, observacao, parcela, status, valor, valor_final) FROM stdin;
    public          postgres    false    215   �,                 0    16606    cliente 
   TABLE DATA           v   COPY public.cliente (id, cep, cidade, cpf_cnpj, data_nascimento, email, endereco, estado, nome, telefone) FROM stdin;
    public          postgres    false    216   �-                 0    16613    cobranca 
   TABLE DATA           I   COPY public.cobranca (id, data, descricao, valor, boleto_id) FROM stdin;
    public          postgres    false    217   //                 0    16619    usuario 
   TABLE DATA           V   COPY public.usuario (id, login, papel, primeiro_nome, senha, ultimo_nome) FROM stdin;
    public          postgres    false    219   �/       &           0    0    billing_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.billing_seq', 1, false);
          public          postgres    false    213            '           0    0 
   boleto_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.boleto_seq', 51, true);
          public          postgres    false    220            (           0    0 
   charge_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.charge_seq', 351, true);
          public          postgres    false    214            )           0    0    cliente_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.cliente_seq', 101, true);
          public          postgres    false    221            *           0    0    cobranca_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cobranca_seq', 101, true);
          public          postgres    false    222            +           0    0    customer_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.customer_seq', 351, true);
          public          postgres    false    209            ,           0    0    debt_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.debt_seq', 501, true);
          public          postgres    false    211            -           0    0    user_seq    SEQUENCE SET     6   SELECT pg_catalog.setval('public.user_seq', 1, true);
          public          postgres    false    210            .           0    0    usuario_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.usuario_id_seq', 8, true);
          public          postgres    false    218            /           0    0 	   uuser_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.uuser_seq', 201, true);
          public          postgres    false    212            }           2606    16605    boleto boleto_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.boleto
    ADD CONSTRAINT boleto_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.boleto DROP CONSTRAINT boleto_pkey;
       public            postgres    false    215                       2606    16612    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    216            �           2606    16617    cobranca cobranca_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.cobranca
    ADD CONSTRAINT cobranca_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.cobranca DROP CONSTRAINT cobranca_pkey;
       public            postgres    false    217            �           2606    16626    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public            postgres    false    219            �           2606    16630 $   cobranca fkdshmcftwk0f6hl03n5jvvefc4    FK CONSTRAINT     �   ALTER TABLE ONLY public.cobranca
    ADD CONSTRAINT fkdshmcftwk0f6hl03n5jvvefc4 FOREIGN KEY (boleto_id) REFERENCES public.boleto(id);
 N   ALTER TABLE ONLY public.cobranca DROP CONSTRAINT fkdshmcftwk0f6hl03n5jvvefc4;
       public          postgres    false    215    217    3453               &  x�]�KN�0E�/��b�i�f�:C"�a'�؍,%v�|�� �F6�s$`9��;�WAy<�}��Z�\�<�J�B�Z��S����X��`�1�����!;�O�4����o&��	��;K9�;	���~,��>��]��c�e�����R���H&~�8��2�8�僰$/��q\�ר�˧q�X@�S�s�f�"&�DAX:&�q��f�&��(@�BdDP�o���ʥ���%V?���(�%��6._�&�+�zy��(z�����&y3�f�����ҵ_E���Ē/<˲o)�~�         "  x�ePMk�0=+���jc9q>n�`l�J��.f��#�J�������?69�a��e�IzOh��ʼ(���&�w���l����ZV���I����m��u�֫��pv����N���졦ذ�\�C�UY�Vf���@�3�9����}���K��:�H��S��'�M�}��v8԰��Y
V	Ѯ�l7I!:�eQ"<�������j]1y��g��z�af��+x���7"��=òK�ov`�%��i�3d��&��J�_-o}�B�]��+H�H�U�$��A{	         �   x�m�1�0���>E.��v��X�3udI��K\��+q.V��Jo�?=eU%�N��s�W���%�atӘ�Ǻ�]�Z�'���?�lx�/���������bN![��~Q�� ����}8�o��2�         �  x�]�I��@�urעL��	2���M������u�(}���&*�z�����5�-�MV���4lh�C���/֫Ū]�$��T���1�U�ռa�O"���5%��y� H�����#���"�'`�l|�nӵ��N�ɰ�Ks��ʿ��׊�(��c��+��o�B�|3��t�exДQ���o۝�Uy��!
$��PUw�E���*�ocN5��^Mr�`���J�bFm��(ś_���ي��:��[��ғ
.;�*�	�4겣�ܰ)�I[7�Ѿӎ��K ���;�����JԚ/S��8�7!��%I��e�d�Y��3�hlMQë���Q���b~��;8@�M�4�|s��p*4�b^H����Ol��ѧ��p�v�o^S]�v1��,ދ����	�/�#Ŝ�;5\6��*�,�������O7��X����/��Z2��z̭     