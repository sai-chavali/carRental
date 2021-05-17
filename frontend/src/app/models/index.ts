export interface Car {
    name: string;
    model: string;
    made: Number;
    color: string;
    price: Number;
    status: string;
    id: number;
}


export interface User {
    name: string;
    email: string;
    id: Number;
}


export type CarBookings =  User & {start_time: Date, end_time: Date}

export interface CarBookingsResponse {
    car: Car;
    carBookings: CarBookings[];
}

export interface UserBookingsResponse {
    user: User;
    userBookings: UserBookings[];
}

export interface UserBookings {
    name:string;
    model:string;
    start_time: Date;
    end_time: Date;
    booked_at: Date;
    price: number;
}