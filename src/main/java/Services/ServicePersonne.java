package Services;

import Entite.Personne;
import Utlis.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServicePersonne implements IService<Personne> {
  private   Connection con2= DataSource.getInstance().getCon();

    @Override
    public boolean ajouter(Personne personne) throws SQLException {
        return false;
    }

    @Override
    public boolean supprimer(Personne personne) throws SQLException {
        return false;
    }

    @Override
    public boolean modifier(Personne personne) throws SQLException {
        return false;
    }

    @Override
    public List<Personne> afficher() throws SQLException {
        return List.of();
    }

    @Override
    public Personne findById(int id) throws SQLException {
        return null;
    }
}
