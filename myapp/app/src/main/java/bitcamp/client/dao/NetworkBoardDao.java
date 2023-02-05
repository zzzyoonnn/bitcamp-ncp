package bitcamp.client.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.google.gson.Gson;

import bitcamp.myapp.vo.Board;

public class NetworkBoardDao implements BoardDao {

  DataInputStream in;
  DataOutputStream out;

  public NetworkBoardDao(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Board board) {
    fetch("board", "insert", board);
  }

  @Override
  public Board[] findAll() {
	return new Gson().fromJson(fetch("board", "findAll"), Board[].class);
  }

  @Override
  public Board findByNo(int no) {
	return new Gson().fromJson(fetch("board", "findByNo", no), Board.class);
  }

  @Override
  public void update(Board board) {
	fetch("board", "update", board);
  }

  @Override
  public boolean delete(Board board) {
	fetch("board", "delete", board);
	return true;
  }
  
  public String fetch(String dataName, String action, Object... data) throws DaoException {
	try {
	  out.writeUTF(dataName);
	  out.writeUTF(action);
	  if (data.length > 0) {
	    out.writeUTF(new Gson().toJson(data[0]));
	  }
	  
	  // 응답
	  String status = in.readUTF();
	  if (status.equals("400")) {
		throw new DaoException("클라이언트 요청 오류!");
	  } else if (status.equals("500")) {
		throw new DaoException("서버 실행 오류!");
	  }
	  return in.readUTF();
	} catch (DaoException e) {
	  throw e;
	} catch (Exception e) {
	  throw new DaoException("오류 발생", e);
	}
  }
}























