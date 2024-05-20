--liquibase formatted sql

--changeset adagridjan:1.0.create-table-execution_time_method stripComments:false splitStatements:false runAlways:false runOnChange:true failOnError:true
CREATE TABLE IF NOT EXISTS system_performance_metrics
(
    id           BIGSERIAL PRIMARY KEY,
    created_at  timestamp with time zone DEFAULT now() NOT NULL,
    cpu_usage    VARCHAR(128) NOT NULL,
    memory_usage VARCHAR(128) NOT NULL,
    disk_usage   VARCHAR(128) NOT NULL
);

COMMENT ON TABLE public.system_performance_metrics IS 'Таблица для хранения показатели производительности системы';
COMMENT ON COLUMN public.system_performance_metrics.id IS 'Первичный ключ. Уникальный идентификатор записи';
COMMENT ON COLUMN public.system_performance_metrics.created_at IS 'Дата создания. Не изменяется после создания';
COMMENT ON COLUMN public.system_performance_metrics.cpu_usage IS 'Использование процессора в процентах';
COMMENT ON COLUMN public.system_performance_metrics.memory_usage IS 'Использование оперативной памяти в мегабайтах';
COMMENT ON COLUMN public.system_performance_metrics.disk_usage IS 'Использование диска в гигабайтах';