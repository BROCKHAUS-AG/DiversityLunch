import { FC, Key } from 'react';

export interface IconListProps<T>{
  data: T[],
  itemComponent: any,
  getKeyFunction: (_:T)=> Key | null | undefined,
}

export const IconList = <T extends unknown>(
  { data, itemComponent, getKeyFunction }: IconListProps<T>,
) => {
  const items = data.map((item: T) => <li key={getKeyFunction(item)}>{itemComponent({ project: item })}</li>);
  return (
    <ul>
      {items}
    </ul>
  );
};
