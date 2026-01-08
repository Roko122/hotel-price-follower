import { useState } from 'react';
import { Check, ChevronsUpDown } from 'lucide-react';
import { cn } from '@/lib/utils';
import { Button } from '@/components/ui/button';
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from '@/components/ui/command';
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover';

import type { JSX } from 'react';
import type { Room } from '@/types/Room.js';

type RoomSelectProps = {
  rooms: Room[];
  selectedRoom: string | undefined;
  setSelectedRoom: (selectedRoom: string | undefined) => void;
};

function RoomSelect(props: RoomSelectProps): JSX.Element {
  const { rooms, selectedRoom, setSelectedRoom } = props;
  const [open, setOpen] = useState<boolean>(false);

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button variant="outline" role="combobox" aria-expanded={open} className="w-full justify-between">
          {selectedRoom
            ? (rooms.find((room) => room.id.toString() === selectedRoom)?.roomType ?? 'Valitse huonetyyppi')
            : 'Valitse huonetyyppi'}
          <ChevronsUpDown className="opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-(--radix-popover-trigger-width) p-0">
        <Command>
          <CommandInput placeholder="Hae huoneita" className="h-9" />
          <CommandList>
            <CommandEmpty>No framework found.</CommandEmpty>
            <CommandGroup>
              {rooms.map((room) => (
                <CommandItem
                  key={room.id}
                  value={room.id.toString()}
                  onSelect={(currentValue) => {
                    setSelectedRoom(currentValue === selectedRoom ? '' : currentValue);
                    setOpen(false);
                  }}
                >
                  {room.roomType}
                  <Check className={cn('ml-auto', selectedRoom === room.id.toString() ? 'opacity-100' : 'opacity-0')} />
                </CommandItem>
              ))}
            </CommandGroup>
          </CommandList>
        </Command>
      </PopoverContent>
    </Popover>
  );
}

export default RoomSelect;
