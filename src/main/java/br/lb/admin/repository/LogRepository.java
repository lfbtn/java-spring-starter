package br.lb.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.lb.admin.entity.LogEntry;

public interface LogRepository extends JpaRepository<LogEntry, Integer> {

}