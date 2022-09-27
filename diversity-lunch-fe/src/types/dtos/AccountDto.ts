import { Role } from '../../model/Role';

export type AccountDto = {
  id: number,
  profileId: number,
  role: Role,
}
