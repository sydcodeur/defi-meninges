import { Role } from './role.enum';

export interface User {
    id: string;
    username: string;
    email: string;
    role: Role;
    totalScore: number;
}