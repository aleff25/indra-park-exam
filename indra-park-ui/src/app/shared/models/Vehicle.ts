import {Operation} from './Operation';

export interface Vehicle {

  plate: string;
  model: string;
  operation: Operation;
  createdAt: Date;
  updatedAt: Date;

}


export class VehicleBuilder implements Vehicle {

  createdAt: Date;
  model: string;
  operation: Operation = Operation.ENTRANCE;
  plate: string;
  updatedAt: Date;

  constructor(plate, model) {
    this.plate = plate;
    this.model = model;
  }

}
