package com.pi.asistencia_app.commons;

public interface ICrudCommonsDto<DtoReq, DtoRes, Id> {
    public DtoRes save(DtoReq dtoReq);
    public DtoRes update(Id id, DtoReq dtoReq);
    public DtoRes findById(Id id);
    public DtoRes delete(Id id);
}
