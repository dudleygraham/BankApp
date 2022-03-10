package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Statement;

public interface StatementService {
	public Statement addStatement(Statement Statement);
	public Optional<Statement> getStatementById (Long id);
	public List<Statement> getAllStatements();
	public String deleteStatementById(Long id);
	public Statement updateStatement(Statement Statement);
	public List<Statement> getAllStatementsAscOrder();
	public List<Statement> getAllStatementsDescOrder();
	public boolean existsbyId(long id);
}
