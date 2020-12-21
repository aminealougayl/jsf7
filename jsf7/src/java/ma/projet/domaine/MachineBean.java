/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.domaine;

import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Machine;
import ma.projet.beans.Salle;
import ma.projet.service.MachineService;
import ma.projet.service.SalleService;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author LACHGAR
 */

@ManagedBean(name = "machineBean")
public class MachineBean {
    private Machine machine;

    private Salle salle;
    private List <Machine> machines;
    private MachineService machineService;
    private SalleService salleService;

    public MachineBean() {
        machine = new Machine();
        machineService = new MachineService();
        salleService = new SalleService();
    }

    public List<Machine> getMachines() {
        if(machines == null)
            machines = machineService.getAll();
            return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public MachineService getMachineService() {
        return machineService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    
    public String onCreateAction(){
        machineService.create(machine);
        return null;
    }
    public String onDeleteAction(){
        
        machineService.delete(machineService.getById(machine.getId()));
        return null;
    }
     public List<Machine> salleLoad(){
         for(Machine m : machineService.getAll()){
             if(m.getSalle().equals(salle)){
                machines.add(m);
             }
         }
        return machines;
        
       
    }
      public void load(){
          System.out.println(salle.getLibelle());
        salle = salleService.getById(salle.getId());
        getMachines();
    }
      public void onEdit(RowEditEvent event) {
        machine = (Machine) event.getObject();
        Salle salle = salleService.getById(this.machine.getSalle().getId());
        machine.setSalle(salle);
        machine.getSalle().setLibelle(salle.getLibelle());
        machineService.update(machine);
   }
     
    public void onCancel(RowEditEvent event) {
   }
}
