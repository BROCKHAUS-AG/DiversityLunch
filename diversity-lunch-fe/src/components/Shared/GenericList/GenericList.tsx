import { FC, Key } from 'react';

export interface GenericListItemComponentProp<T>{item: T}

export interface GenericListProps<T>{
  data: T[],
  itemComponent: FC<GenericListItemComponentProp<T>>,
  getKeyFunction: (_:T)=> Key | null | undefined,
}

export const GenericList = <T extends unknown>(
  { data, itemComponent, getKeyFunction }: GenericListProps<T>,
) => {
  const items = data.map((item: T) => (
    <li key={getKeyFunction(item)}>
      {itemComponent({ item })}
    </li>
  ));

  return (
    <ul>
      {items}
    </ul>
  );
};
