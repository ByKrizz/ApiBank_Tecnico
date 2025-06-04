
import com.byKrizz.cuentas.domain.model.Cuenta;
import com.byKrizz.cuentas.domain.ports.in.CreateCuentaService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class CuentaService {

    private final CreateCuentaService createCuentaService;

    public CuentaService(CreateCuentaService createCuentaService) {
        this.createCuentaService = createCuentaService;
    }

    public Cuenta crearCuenta(Cuenta cuenta) {
        return createCuentaService.crearCuenta(cuenta);
    }
}
