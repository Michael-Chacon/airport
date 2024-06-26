package org.vuelosGlobales.generals.role.adapter.in;

import org.vuelosGlobales.generals.role.domain.Role;
import org.vuelosGlobales.generals.role.application.RoleService;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;

import java.util.List;

public class RoleConsoleAdap {
    private final RoleService roleService;
    Console console = new Console();
    public RoleConsoleAdap(RoleService roleService) {
        this.roleService = roleService;
    }

    public  void crudRole(){
        menuRole: while (true){
            System.out.println("1. Crear Rol");
            System.out.println("2. Actualizar Rol");
            System.out.println("3. Buscar Rol por ID");
            System.out.println("4. Eliminar Rol");
            System.out.println("5. Listar todos Roles");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar un rol", "*");
                    String name = console.stringNotNull("Nombre del rol: ");
                    Role role = new Role();
                    role.setName(name);
                    roleService.createRole(role);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de un rol", "*");
                    showRoles();
                    Role roleSelect = Helpers.transformAndValidateObj(
                            () -> roleService.getRoleById(console.readInt("Seleccione el rol por el id: "))
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + roleSelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del rol: ");
                    roleSelect.setName(newName);
                    roleService.updateRole(roleSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un rol", "*");
                    System.out.println();
                    showRoles();
                    Role showRole = Helpers.transformAndValidateObj(
                            () -> roleService.getRoleById(console.readInt("Seleccione el rol por el id: "))
                    );
                    System.out.println(showRole);
                    System.out.println();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un rol", "*");
                    showRoles();
                    Role showRoleF = Helpers.transformAndValidateObj(
                            () -> roleService.getRoleById(console.readInt("Seleccione el rol por el id: "))
                    );
                    int roleDelete = showRoleF.getId();
                    roleService.deleteRole(roleDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Rol eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Roles registrados", "*");
                    showRoles();
                    System.out.println();
                    break;
                case 6:
                    break menuRole;
            }
        }
    }

    public void showRoles(){
        List<Role> roleList = roleService.getAllRoles();
        System.out.println("Listado de roles:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        roleList.forEach(role -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.println(String.format("\n| %-4s | %-16s |", role.getId(), role.getName()));
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }
}
